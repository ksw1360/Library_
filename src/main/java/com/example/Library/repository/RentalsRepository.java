package com.example.Library.repository;

import org.hibernate.annotations.DialectOverride.SQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Library.entity.Rentals;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Long> {

        // 서적 대출 - 초기 대출 정보 생성
        @Modifying
        @Query(value = "insert into rentals (user_id, book_id, rent_date, due_date, status, create_date, create_user) "
                        +
                        " values (:userId, :bookId, :rentDate, :dueDate, :status, :createDate, :createUser)", nativeQuery = true)
        void insertRental(@Param("userId") Integer userId, @Param("bookId") Integer bookId,
                        @Param("rentDate") Timestamp rentDate,
                        @Param("dueDate") Timestamp dueDate, @Param("status") String status,
                        @Param("createDate") Timestamp createDate,
                        @Param("createUser") String createUser);

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

        @Query("SELECT new com.project.dto.RentalListDTO(r.id, u.username, b.bookname, r.rentDate, r.dueDate, r.status) "
                        +
                        "FROM Rentals r JOIN r.book b JOIN r.user u")
        List<RentalListDTO> findRentalListWithDetails();
}