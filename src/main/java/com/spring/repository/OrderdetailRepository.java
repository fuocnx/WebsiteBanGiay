package com.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.OrderDetail;

public interface OrderdetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query(value = "SELECT *\r\n" + "FROM order_detail where  trangthai = 'true'", nativeQuery = true)
	List<OrderDetail> findAllOderdeatil();

	@Query(value = "SELECT *\r\n" + "FROM order_detail where  trangthai = 'false'", nativeQuery = true)
	List<OrderDetail> findAllOderdeatilfasle();

	@Query(value = "SELECT *\r\n" + "FROM order_detail where  trangthai = 'false'", nativeQuery = true)
	Page<OrderDetail> findAllOderdeatifasle(Pageable pageable);

	@Query(value = "SELECT COUNT(*)\r\n" + "  FROM order_detail\r\n" + "  WHERE trangthai = 'true'", nativeQuery = true)
	int sl();

	@Query(value = "SELECT * \r\n" + "  FROM order_detail\r\n" + "  WHERE orders_id=?1", nativeQuery = true)
	List<OrderDetail> findAllByid(Integer id);

	@Query(value = " select * from order_detail where orders_id is null", nativeQuery = true)
	List<OrderDetail> findAllOderdeatil1();
}
