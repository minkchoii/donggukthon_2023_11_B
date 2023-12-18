package rednosed.app.repository.rds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rednosed.app.domain.rds.LikeStamp;
import rednosed.app.domain.rds.User;
import rednosed.app.dto.response.StampLikeDataTmpDto;

import java.util.List;


@Repository
public interface LikeStampRepository extends JpaRepository<LikeStamp, Long> {
    @Query("SELECT ls FROM LikeStamp ls JOIN FETCH ls.stamp WHERE ls.user = :user")
    List<LikeStamp> findLikeStampByUser(@Param("user") User user);

    @Query("SELECT new rednosed.app.dto.response.StampLikeDataTmpDto(s.stampClientId, s.stampImgUrl, s.stampName, COUNT(ls)) " +
            "FROM LikeStamp ls JOIN ls.stamp s " +
            "WHERE ls.user.userClientId = :userClientId " +
            "GROUP BY s.stampClientId, s.stampImgUrl, s.stampName")
    List<StampLikeDataTmpDto> findStampLikeDataByUserClientId(@Param("userClientId") String userClientId);

}
