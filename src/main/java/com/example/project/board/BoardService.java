package com.example.project.board;

import com.example.project._core.enums.AlbumEnum;
import com.example.project._core.errors.exception.Exception401;
import com.example.project._core.utils.ImageVideoUtil;
import com.example.project.album.Album;
import com.example.project.album.AlbumRepository;
import com.example.project.bookmark.BookmarkRepository;
import com.example.project.like.Like;
import com.example.project.like.LikeRepository;
import com.example.project.reply.ReplyRepository;
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
    private final BookmarkRepository bookRepository;

    public BoardResponse.BoardListDTO boardList(Integer userId) {
        List<Board> boards = boardRepository.findByBoards(userId);
        List<BoardResponse.BoardListDTO.BoardDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            Integer likeCount = likeRepository.findByLikeCount(board.getId());
            Integer replyCount = replyRepository.replyCount(board.getId());
            List<Album> albums = albumRepository.findByBoardId(board.getId());
            List<BoardResponse.BoardListDTO.AlbumDTO> albumDTOs = albums.stream()
                    .map(BoardResponse.BoardListDTO.AlbumDTO::new)
                    .toList();

            // 좋아요 여부 확인
            Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;

            // 북마크 여부 확인
            Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;

            // BoardDTO 객체 생성
            BoardResponse.BoardListDTO.BoardDTO boardDTO = new BoardResponse.BoardListDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked);
            boardDTOs.add(boardDTO);
        }

        return new BoardResponse.BoardListDTO(boardDTOs);
    }

    public BoardResponse.BoardListDTO boardList(int socialId, Integer userId) {
        List<Board> boards = boardRepository.findByBoardSocialId(socialId);
        List<BoardResponse.BoardListDTO.BoardDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            Integer likeCount = likeRepository.findByLikeCount(board.getId());
            Integer replyCount = replyRepository.replyCount(board.getId());
            List<Album> albums = albumRepository.findByBoardId(board.getId());
            List<BoardResponse.BoardListDTO.AlbumDTO> albumDTOs = albums.stream()
                    .map(BoardResponse.BoardListDTO.AlbumDTO::new)
                    .toList();

            // 좋아요 여부 확인
            Boolean liked = likeRepository.findByLikeUserId(board.getId(), userId) > 0;

            // 북마크 여부 확인
            Boolean bookmarked = bookRepository.findByBookUserId(board.getId(), userId) > 0;

            // BoardDTO 객체 생성
            BoardResponse.BoardListDTO.BoardDTO boardDTO = new BoardResponse.BoardListDTO.BoardDTO(board, likeCount, replyCount, albumDTOs, board.getUserId().getImage(), liked, bookmarked);
            boardDTOs.add(boardDTO);
        }

        return new BoardResponse.BoardListDTO(boardDTOs);
    }

    @Transactional
    public void save(Integer socialId, BoardRequest.SaveDTO reqDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("유저 정보가 없습니다."));

        Social social = socialRepository.findById(socialId)
                .orElseThrow(() -> new Exception401("찾고자 하는 소셜이 없습니다."));

        Board board = boardRepository.save(reqDTO.boardToEntity(social, user));

        // 이미지 파일 처리
        for (int i = 0; i < reqDTO.getImgFiles().size() - 1; i++) {
            if (i >= 0) {
                MultipartFile imgFile = reqDTO.getImgFiles().get(i);
                ImageVideoUtil.FileUploadResult a = ImageVideoUtil.uploadFile(imgFile);
                String imgPath = a.getFilePath();

                albumRepository.save(reqDTO.albumToEntity(user, board, imgPath, AlbumEnum.IMAGE));
            }
        }

        // 동영상 파일 처리
        for (int i = 0; i < reqDTO.getVideoFiles().size() - 1; i++) {
            if (i >= 0) {
                MultipartFile videoFile = reqDTO.getVideoFiles().get(i);
                ImageVideoUtil.FileUploadResult a = ImageVideoUtil.uploadFile(videoFile);
                String videoPath = a.getFilePath();

                albumRepository.save(reqDTO.albumToEntity(user, board, videoPath, AlbumEnum.VIDEO));
            }
        }
    }
}
