package com.janggoback.tripwith.trip.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrip is a Querydsl query type for Trip
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrip extends EntityPathBase<Trip> {

    private static final long serialVersionUID = 1811115559L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrip trip = new QTrip("trip");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> endAttractionId = createNumber("endAttractionId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final com.janggoback.tripwith.member.domain.QMember host;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> startAttractionId = createNumber("startAttractionId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final ListPath<com.janggoback.tripwith.triprequest.domain.TripRequest, com.janggoback.tripwith.triprequest.domain.QTripRequest> tripRequests = this.<com.janggoback.tripwith.triprequest.domain.TripRequest, com.janggoback.tripwith.triprequest.domain.QTripRequest>createList("tripRequests", com.janggoback.tripwith.triprequest.domain.TripRequest.class, com.janggoback.tripwith.triprequest.domain.QTripRequest.class, PathInits.DIRECT2);

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QTrip(String variable) {
        this(Trip.class, forVariable(variable), INITS);
    }

    public QTrip(Path<? extends Trip> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrip(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrip(PathMetadata metadata, PathInits inits) {
        this(Trip.class, metadata, inits);
    }

    public QTrip(Class<? extends Trip> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.host = inits.isInitialized("host") ? new com.janggoback.tripwith.member.domain.QMember(forProperty("host")) : null;
    }

}

