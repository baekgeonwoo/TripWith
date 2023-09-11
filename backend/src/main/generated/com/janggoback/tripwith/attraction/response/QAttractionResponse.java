package com.janggoback.tripwith.attraction.response;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttractionResponse is a Querydsl query type for AttractionResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttractionResponse extends EntityPathBase<AttractionResponse> {

    private static final long serialVersionUID = -1987895761L;

    public static final QAttractionResponse attractionResponse = new QAttractionResponse("attractionResponse");

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

    public QAttractionResponse(String variable) {
        super(AttractionResponse.class, forVariable(variable));
    }

    public QAttractionResponse(Path<? extends AttractionResponse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttractionResponse(PathMetadata metadata) {
        super(AttractionResponse.class, metadata);
    }

}

