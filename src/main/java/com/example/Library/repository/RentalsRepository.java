package com.example.Library.repository;

import org.hibernate.annotations.DialectOverride.SQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Library.dto.RentalListDTO;
import com.example.Library.entity.Rentals;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Long> {

        // 서적 대출 - 초기 대출 정보 생성
        @Modifying
        @Query(value = "insert into rentals (user_id, book_id, rent_date, due_date, status, createdate, createuser) "
                        +
                        " values (:userId, :bookId, :rentDate, :dueDate, :status, :createdate, :createuser)", nativeQuery = true)
        void insertRental(@Param("userId") Integer userId, @Param("bookId") Integer bookId,
                        @Param("rentDate") Timestamp rentDate,
                        @Param("dueDate") Timestamp dueDate, @Param("status") String status,
                        @Param("createdate") Timestamp createdate,
                        @Param("createuser") String createUser);

        // 서적 반납
        @Modifying
        @Query(value = "UPDATE rentals SET return_date = :returnDate, status = :status, overdue_days = :overdueDays, overdue_fee = :overdueFee "
                        +
                        "WHERE id = :rentalId", nativeQuery = true)
        void updateRental(@Param("returnDate") Timestamp returnDate, @Param("status") String status,
                        @Param("overdueDays") Integer overdueDays, @Param("overdueFee") Integer overdueFee, // 📌
                                                                                                            // Double을
                                                                                                            // Integer로
                                                                                                            // 수정
                        @Param("rentalId") Long rentalId);

        // 쿼리에 nativeQuery = true 를 추가하고, 실제 DB 테이블명과 컬럼명으로 작성합니다.
        @Query(value = "SELECT r.id, u.username, b.bookname, r.rent_date AS rentDate, r.due_date AS dueDate, r.status "
                        +
                        "FROM rentals r " +
                        "JOIN books b ON r.book_id = b.id " +
                        "JOIN users u ON r.user_id = u.id", nativeQuery = true)
        List<RentalListDTO> findRentalListWithDetails();
        // List<RentalListDTO>
        // 주의: 네이티브 쿼리를 쓰면 DTO로 바로 매핑하기 까다로워서 List<Object[]> 로 받아야 할 수도 있습니다.

        // @Query("SELECT new com.project.dto.RentalListDTO(r.id, u.username,
        // b.bookname, r.rentDate, r.dueDate, r.status) "
        // +
        // "FROM Rentals r JOIN r.book b JOIN r.user u")
        // List<RentalListDTO> findRentalListWithDetails();
}