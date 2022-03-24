package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.board.model.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
	
	@Query(nativeQuery = true,
			value = "select p.*, (6371 * acos(cos(radians(?1)) * cos(radians(p.latitude)) * " +
			" cos(radians(p.longitude) - radians(?2)) + sin(radians(?3)) * " +
			" sin(radians(p.latitude)))) as distance " +
			" from Point p " +
			"having distance <= ?4 " +
			" order by distance")
	// nativeQuery 를 사용하여 쿼리 작성을 함.
	// nativeQuery 를 작성시 파라미터를 ?n 으로 실행할수 있다.
	// ?1 의 n 은 순서를 의미하며 파라미터 선언 순서다.
	// 참고로 ?1 을 2번 써야 해도 ?1 를 한번 더 사용할수 없기에 입력받는 파라미터를 여러번 받아야 한다.
			public List<Point> findByLatLng(double lat1, double lng, double lat2, int km);
}