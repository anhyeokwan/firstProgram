package com.example.firstprogram.board.repository.custom;

import com.example.firstprogram.entity.QQna;
import com.example.firstprogram.entity.Qna;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Log4j2
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

        int maxIdx = 0;

        try {
            maxIdx = queryFactory
                    .select(qQna.idx.max())
                    .from(qQna)
                    .fetchOne();
        }catch (NullPointerException e){
            maxIdx = 0;
        }
        /*JPQLQuery<Qna> jpqlQuery = from(qQna);
        jpqlQuery.select(qQna.idx.max());

        Qna maxObj = jpqlQuery.fetchOne();*/

        return maxIdx;
    }
}
