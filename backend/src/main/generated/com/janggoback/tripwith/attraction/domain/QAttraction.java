package com.janggoback.tripwith.attraction.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttraction is a Querydsl query type for Attraction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttraction extends EntityPathBase<Attraction> {

    private static final long serialVersionUID = 1448348139L;

    public static final QAttraction attraction = new QAttraction("attraction");

    public final StringPath address = createString("address");

    public final StringPath content = createString("content");

    public final NumberPath<Long> contentTypeId = createNumber("contentTypeId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img1 = createString("img1");

    public final StringPath img2 = createString("img2");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedTime = createDateTime("modifiedTime", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> sigunguCode = createNumber("sigunguCode", Long.class);

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QAttraction(String variable) {
        super(Attraction.class, forVariable(variable));
    }

    public QAttraction(Path<? extends Attraction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttraction(PathMetadata metadata) {
        super(Attraction.class, metadata);
    }

}

