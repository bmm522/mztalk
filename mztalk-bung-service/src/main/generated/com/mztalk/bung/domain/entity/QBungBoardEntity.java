//package com.mztalk.bung.domain.entity;
//
//import static com.querydsl.core.types.PathMetadataFactory.*;
//
//import com.querydsl.core.types.dsl.*;
//
//import com.querydsl.core.types.PathMetadata;
//import javax.annotation.processing.Generated;
//import com.querydsl.core.types.Path;
//
//
///**
// * QBungBoardEntity is a Querydsl query type for BungBoardEntity
// */
//@Generated("com.querydsl.codegen.DefaultEntitySerializer")
//public class QBungBoardEntity extends EntityPathBase<BungBoardEntity> {
//
//    private static final long serialVersionUID = -766155102L;
//
//    public static final QBungBoardEntity bungBoardEntity = new QBungBoardEntity("bungBoardEntity");
//
//    public final StringPath boardContent = createString("boardContent");
//
//    public final NumberPath<Long> boardCount = createNumber("boardCount", Long.class);
//
//    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);
//
//    public final StringPath boardStatus = createString("boardStatus");
//
//    public final StringPath boardTitle = createString("boardTitle");
//
//    public final StringPath boardWriter = createString("boardWriter");
//
//    public final StringPath category = createString("category");
//
//    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);
//
//    public final DatePath<java.sql.Date> deadlineDate = createDate("deadlineDate", java.sql.Date.class);
//
//    public final NumberPath<Long> fullGroup = createNumber("fullGroup", Long.class);
//
//    public final DateTimePath<java.sql.Timestamp> modifyDate = createDateTime("modifyDate", java.sql.Timestamp.class);
//
//    public final NumberPath<Long> nowGroup = createNumber("nowGroup", Long.class);
//
//    public QBungBoardEntity(String variable) {
//        super(BungBoardEntity.class, forVariable(variable));
//    }
//
//    public QBungBoardEntity(Path<? extends BungBoardEntity> path) {
//        super(path.getType(), path.getMetadata());
//    }
//
//    public QBungBoardEntity(PathMetadata metadata) {
//        super(BungBoardEntity.class, metadata);
//    }
//
//}

