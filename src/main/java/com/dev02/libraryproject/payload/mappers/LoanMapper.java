package com.dev02.libraryproject.payload.mappers;

import com.dev02.libraryproject.entity.concretes.business.Loan;
import com.dev02.libraryproject.payload.request.business.LoanRequest;
import com.dev02.libraryproject.payload.response.business.BookResponse;
import com.dev02.libraryproject.payload.response.business.LoanResponse;
import com.dev02.libraryproject.payload.response.business.LoanResponseWithUser;
import com.dev02.libraryproject.service.business.BookService;
import com.dev02.libraryproject.service.helper.MethodHelper;
import com.dev02.libraryproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoanMapper {

    private final BookResponse bookResponse;
    private final BookService bookService;
    private final UserService userService;
    private final MethodHelper methodHelper;

    public Loan mapLoanRequestToLoan(LoanRequest loanRequest){
        return Loan.builder().userId(loanRequest.getUserId())
                .bookId(loanRequest.getBookId())
                .notes(loanRequest.getNotes())
                .build();
    }

    public LoanResponse mapLoanToLoanResponse(Loan loan){
        return LoanResponse.builder()
                .id(loan.getId())
                .userId(loan.getUserId())
                .bookId(loan.getBookId())
                .book(bookService.findBookById(loan.getBookId())) //her response içinde Book gönderiliyor
                                                                  //Eğer Loan Response larda gerekmeyen varsa ayrı bir mapper oluşturulacak
                .build();
    }

    public LoanResponseWithUser mapLoanToLoanResponseWithUser(Loan loan){
        return LoanResponseWithUser.builder()
                .id(loan.getId())
                .bookId(loan.getBookId())
                .user(methodHelper.isUserExist(loan.getUserId()))
                .build();
    }

}
