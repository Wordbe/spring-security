package co.wordbe.springsecurity.repository;

import co.wordbe.springsecurity.domain.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {

    @Query("SELECT r " +
            "FROM Resources r " +
            "JOIN FETCH r.roleSet " +
            "WHERE r.resourceType = 'url' " +
            "ORDER BY r.orderNum DESC")
    List<Resources> findAllResources();

    @Query("select r " +
            "from Resources r " +
            "join fetch r.roleSet " +
            "where r.resourceType = 'method' " +
            "order by r.orderNum desc")
    List<Resources> findAllMethodResources();

    @Query("select r from Resources r join fetch r.roleSet where r.resourceType = 'pointcut' order by r.orderNum desc")
    List<Resources> findAllPointcutResources();
}
