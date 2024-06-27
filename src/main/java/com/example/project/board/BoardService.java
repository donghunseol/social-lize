package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.errors.exception.Exception403;
import com.example.project._core.errors.exception.Exception404;
import com.example.project._core.utils.HlsUtil;
import com.example.project._core.utils.ImageVideoUtil;
import com.example.project.album.Album;
import com.example.project.album.AlbumRepository;
import com.example.project.bookmark.BookmarkRepository;
import com.example.project.hashtag.Hashtag;
import com.example.project.hashtag.HashtagRepository;
import com.example.project.like.LikeRepository;
import com.example.project.reply.Reply;
import com.example.project.reply.ReplyRepository;
import com.example.project.rereply.Rereply;
import com.example.project.rereply.RereplyRepository;
import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import com.example.project.user.User;
import com.example.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final SocialRepository socialRepository;
    private final AlbumRepository albumRepository;
    private final LikeRepository likeRepository;
    private final ReplyRepository replyRepository;
    private final RereplyRepository rereplyRepository;
    private final BookmarkRepository bookRepository;
    private final HashtagRepository hashtagRepository;

    public BoardResponse.SocialDetailDTO boardList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        List<Board> boards = boardRepository.findByBoards(userId);
        List<BoardResponse.SocialDetailDTO.BoardDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            Integer likeCount = likeRepository.findByLikeCount(board.getId());
            Integer replyCount = replyRepository.replyCount(board.getId());
            List<Album> albums = albumRepository.findByBoardId(board.getId());
            List<BoardResponse.SocialDetailDTO.AlbumDTO> albumDTOs = albums.stream()
                    .map(BoardResponse.SocialDetailDTO.AlbumDTO::new)
                    .toList();

            // 좋아요 여부 확인
            Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;

            // 북마크 여부 확인
            Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;

            List<Hashtag> hashtags = hashtagRepository.findByBoardId(board.getId());

            Boolean hashEmpty = false;

            // BoardDTO 객체 생성
            BoardResponse.SocialDetailDTO.BoardDTO boardDTO = new BoardResponse.SocialDetailDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked, hashtags, user.getImage(), hashEmpty);

            if (boardDTO != null && boardDTO.getHashtagList() != null && !boardDTO.getHashtagList().isEmpty()) {
                if (boardDTO.getHashtagList() != null && !boardDTO.getHashtagList().isEmpty()) {
                    if (boardDTO.getHashtagList().get(0).getName().equals("")) {
                        hashEmpty = true;
                        boardDTO = new BoardResponse.SocialDetailDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked, hashtags, user.getImage(), hashEmpty);
                        boardDTOs.add(boardDTO);
                    }
                }
            }

            boardDTOs.add(boardDTO);
        }

        return new BoardResponse.SocialDetailDTO(boardDTOs);
    }

    @Transactional
    public void save(Integer socialId, BoardRequest.SaveDTO reqDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("유저 정보가 없습니다."));

        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception401("찾고자 하는 소셜이 없습니다."));

        Board board = boardRepository.save(reqDTO.boardToEntity(social, user));

        // 이미지 파일 처리
        if (reqDTO.getImgFiles() != null) {
            for (int i = 0; i < reqDTO.getImgFiles().size() - 1; i++) {
                if (i >= 0) {
                    MultipartFile imgFile = reqDTO.getImgFiles().get(i);
                    ImageVideoUtil.FileUploadResult a = ImageVideoUtil.uploadFile(imgFile);
                    String imgPath = a.getFilePath();

                    albumRepository.save(reqDTO.albumToEntity(user, board, imgPath, AlbumEnum.IMAGE));
                }
            }
        }

        // 동영상 파일 처리
        if (reqDTO.getVideoFiles() != null) {
            for (int i = 0; i < reqDTO.getVideoFiles().size() - 1; i++) {
                if (i >= 0) {
                    MultipartFile videoFile = reqDTO.getVideoFiles().get(i);
                    ImageVideoUtil.FileUploadResult a = ImageVideoUtil.uploadFile(videoFile);
                    String videoPath = a.getFilePath();

                    // HLS 변환을 수행하고 변환된 파일 경로를 얻어옴
                    String hlsPath = HlsUtil.getConvertVideoPath(videoPath);
                    HlsUtil.convertHls(videoPath);

                    albumRepository.save(reqDTO.albumVideoToEntity(user, board, videoPath, hlsPath, AlbumEnum.VIDEO));
                }
            }
        }

        // 해시태그 처리
        if (reqDTO.getHashtags() != null) {
            for (String hashtag : reqDTO.getHashtags()) {
                String cleanHashtag = hashtag.replaceAll("[\"\\[\\]]", "");
                Hashtag hashtagEntity = new Hashtag();
                hashtagEntity.setName(cleanHashtag);
                hashtagEntity.setBoardId(board);
                hashtagRepository.save(hashtagEntity);
            }
        }
    }

    public BoardResponse.BoardDetailDTO detail(Integer boardId) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시물을 찾을 수 없습니다"));

        User user = userRepository.findById(board.getUserId().getId())
                .orElseThrow(() -> new Exception403("로그인 하셔야 합니다"));

        List<Hashtag> hashtags = hashtagRepository.findByBoardId(board.getId());

        Integer likeCount = likeRepository.findByLikeCount(board.getId());

        Integer replyCount = replyRepository.replyCount(board.getId());

        List<Reply> replies = replyRepository.findByReply(board.getId());

        // 댓글별로 대댓글을 조회하여 DTO로 변환하는 로직
        List<BoardResponse.BoardDetailDTO.ReplyDTO> replyDTOList = replies.stream().map(reply -> {
            List<Rereply> rereplies = rereplyRepository.findByReplyId(reply.getId());
            List<BoardResponse.BoardDetailDTO.RereplyDTO> rereplyDTOList = rereplies.stream()
                    .map(rereply -> new BoardResponse.BoardDetailDTO.RereplyDTO(rereply))
                    .toList();
            return new BoardResponse.BoardDetailDTO.ReplyDTO(reply, rereplyDTOList);
        }).toList();

        return new BoardResponse.BoardDetailDTO(board, user, likeCount, replyCount, hashtags, replyDTOList);
    }

    // 유저 작성 게시글 리스트 조회
    public List<BoardResponse.BoardList> getBoardList() {
        return boardRepository.findAllBoardList();
    }

    // 유저 작성 게시글 상세 조회
    public BoardResponse.Detail getBoardDetail(Integer boardId) {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        return new BoardResponse.Detail(board);
    }
}
