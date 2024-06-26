package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.enums.DeleteStateEnum;
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
import com.example.project.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public BoardResponse.MyBoardAndReplyListDTO myBoardAndReply(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        List<Board> myBoardList = boardRepository.findAllUserId(userId);
        List<Board> myBoardReplyList = boardRepository.findBoardsByUserReplies(userId);

        List<BoardResponse.MyBoardAndReplyListDTO.MyBoardListDTO> boardDTOs = myBoardList.stream()
                .map(board -> {
                    Integer likeCount = likeRepository.findByLikeCount(board.getId());
                    Integer replyCount = replyRepository.replyCount(board.getId());
                    List<Album> albums = albumRepository.findByBoardId(board.getId());
                    List<BoardResponse.MyBoardAndReplyListDTO.MyBoardListDTO.AlbumDTO> albumDTOs = albums.stream()
                            .map(BoardResponse.MyBoardAndReplyListDTO.MyBoardListDTO.AlbumDTO::new)
                            .collect(Collectors.toList());
                    Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;
                    Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;
                    List<Hashtag> hashtags = hashtagRepository.findByBoardId(board.getId());
                    Reply reply = replyRepository.findByBoardId(board.getId());
                    Boolean hashEmpty = hashtags.isEmpty();

                    return new BoardResponse.MyBoardAndReplyListDTO.MyBoardListDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(),
                            liked, bookmarked, hashtags, user.getImage(), hashEmpty, reply);
                })
                .collect(Collectors.toList());

        List<BoardResponse.MyBoardAndReplyListDTO.MyReplyListDTO> replyBoardDTOs = myBoardReplyList.stream()
                .map(board -> {
                    Integer likeCount = likeRepository.findByLikeCount(board.getId());
                    Integer replyCount = replyRepository.replyCount(board.getId());
                    List<Album> albums = albumRepository.findByBoardId(board.getId());
                    List<BoardResponse.MyBoardAndReplyListDTO.MyReplyListDTO.AlbumDTO> albumDTOs = albums.stream()
                            .map(BoardResponse.MyBoardAndReplyListDTO.MyReplyListDTO.AlbumDTO::new)
                            .collect(Collectors.toList());
                    Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;
                    Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;
                    List<Hashtag> hashtags = hashtagRepository.findByBoardId(board.getId());
                    Boolean hashEmpty = hashtags.isEmpty();
                    Reply reply = replyRepository.findByBoardId(board.getId());

                    return new BoardResponse.MyBoardAndReplyListDTO.MyReplyListDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(),
                            liked, bookmarked, hashtags, user.getImage(), hashEmpty, reply);
                })
                .collect(Collectors.toList());

        return new BoardResponse.MyBoardAndReplyListDTO(boardDTOs, replyBoardDTOs);
    }
  
    public BoardResponse.BookMarkBoardListDTO boardList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception403("로그인이 필요한 페이지입니다."));

        List<Board> boards = boardRepository.findByBoards(userId);
        List<BoardResponse.BookMarkBoardListDTO.BoardDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            Integer likeCount = likeRepository.findByLikeCount(board.getId());
            Integer replyCount = replyRepository.replyCount(board.getId());
            List<Album> albums = albumRepository.findByBoardId(board.getId());
            List<BoardResponse.BookMarkBoardListDTO.AlbumDTO> albumDTOs = albums.stream()
                    .map(BoardResponse.BookMarkBoardListDTO.AlbumDTO::new)
                    .toList();

            // 좋아요 여부 확인
            Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;

            // 북마크 여부 확인
            Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;

            List<Hashtag> hashtags = hashtagRepository.findByBoardId(board.getId());

            Reply reply = replyRepository.findByBoardId(board.getId());


            Boolean hashEmpty = false;

            // BoardDTO 객체 생성
            BoardResponse.BookMarkBoardListDTO.BoardDTO boardDTO = new BoardResponse.BookMarkBoardListDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked, hashtags, user.getImage(), hashEmpty, reply);

            if (boardDTO != null && boardDTO.getHashtagList() != null && !boardDTO.getHashtagList().isEmpty()) {
                if (boardDTO.getHashtagList() != null && !boardDTO.getHashtagList().isEmpty()) {
                    if (boardDTO.getHashtagList().get(0).getName().equals("")) {
                        hashEmpty = true;
                        boardDTO = new BoardResponse.BookMarkBoardListDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked, hashtags, user.getImage(), hashEmpty, reply);
                        boardDTOs.add(boardDTO);
                    }
                }
            }

            boardDTOs.add(boardDTO);
        }

        return new BoardResponse.BookMarkBoardListDTO(boards.size(), boardDTOs);
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
                    System.out.println("이건 파일경로 : " + videoPath);

                    // HLS 변환을 수행하고 변환된 파일 경로를 얻어옴
                    String hlsPath = HlsUtil.getConvertVideoPath(videoPath);
                    HlsUtil.convertHls(videoPath);

                    System.out.println("저장 대성공!");
                    System.out.println("이건 변환된 파일경로 : " + hlsPath);

                    // 경로 구분자를 바꿔서 저장
                    videoPath = videoPath.replace("\\upload\\", "/upload/");
                    hlsPath = hlsPath.replace("\\upload\\", "/upload/");
                    hlsPath = hlsPath.replace("/upload", "");
                    hlsPath = hlsPath.replace("./convert/", "/convert/");

                    System.out.println("경로 변환 비디오 : " + videoPath);
                    System.out.println("경로 변환 HLS : " + hlsPath);

                    albumRepository.save(reqDTO.albumVideoToEntity(user, board, hlsPath, videoPath, AlbumEnum.VIDEO));
                }
            }
        }

        // 해시태그 처리
        if (reqDTO.getHashtags() != null && reqDTO.getHashtags().length > 0) {
            String firstHashtag = reqDTO.getHashtags()[0];

            // 첫 번째 요소가 빈 문자열이거나 "[]" 인지 확인
            if (!firstHashtag.isEmpty() && !firstHashtag.equals("[]")) {
                for (String hashtag : reqDTO.getHashtags()) {
                    // 각 해시태그에서 불필요한 대괄호를 제거
                    String cleanHashtag = hashtag.replaceAll("[\"\\[\\]]", "").trim();
                    Hashtag hashtagEntity = new Hashtag();
                    hashtagEntity.setName(cleanHashtag);
                    hashtagEntity.setBoardId(board);
                    hashtagRepository.save(hashtagEntity);

                    System.out.println(cleanHashtag);
                }
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
    public BoardResponse.BoardListDTO getBoardList() {
        Integer count = boardRepository.findByBoardRole();
        List<Board> boardListDTO = boardRepository.findAllBoardList();
        List<BoardResponse.BoardListDTO.BoardList> boardList = boardListDTO.stream()
                .map(BoardResponse.BoardListDTO.BoardList::new)
                .collect(Collectors.toList());

        return new BoardResponse.BoardListDTO(count, boardList);
    }

    // 유저 작성 게시글 상세 조회
    public BoardResponse.Detail getBoardDetail(Integer boardId) {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        return new BoardResponse.Detail(board);
    }

    @Transactional
    public void delete(Integer userId, Integer boardId) {
        Board board = boardRepository.findByBoardIdAndUserId(boardId, userId)
                .orElseThrow(() -> new Exception403("게시글을 삭제할 권한이 없습니다."));

        board.setState(DeleteStateEnum.DELETED);
    }
}
