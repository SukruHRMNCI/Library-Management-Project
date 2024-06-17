package com.dev02.libraryproject.repository.business;

import com.dev02.libraryproject.entity.concretes.business.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Page<Loan> findByUser_IdEquals(Long id, Pageable pageable);
}
