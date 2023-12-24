package com.example.firstprogram.board.repository.custom;

import com.example.firstprogram.entity.QQna;
import com.example.firstprogram.entity.Qna;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

public class QnaCustomImpl extends QuerydslRepositorySupport implements QnaCustom{

    private final JPQLQueryFactory queryFactory;

    // QuerydslRepositorySupport 상속 시 필수
    public QnaCustomImpl(JPQLQueryFactory queryFactory){
        super(Qna.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public int findQnaMaxIdx() {
        QQna qQna = QQna.qna;
        int maxIdx = queryFactory
                .select(qQna.idx.max())
                .from(qQna)
                .fetchOne();

        /*JPQLQuery<Qna> jpqlQuery = from(qQna);
        jpqlQuery.select(qQna.idx.max());

        Qna maxObj = jpqlQuery.fetchOne();*/

        return maxIdx;
    }
}
