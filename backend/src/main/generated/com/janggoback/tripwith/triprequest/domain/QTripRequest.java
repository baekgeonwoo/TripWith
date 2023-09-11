package com.janggoback.tripwith.triprequest.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTripRequest is a Querydsl query type for TripRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTripRequest extends EntityPathBase<TripRequest> {

    private static final long serialVersionUID = -155802747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripRequest tripRequest = new QTripRequest("tripRequest");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.janggoback.tripwith.member.domain.QMember requester;

    public final BooleanPath status = createBoolean("status");

    public final com.janggoback.tripwith.trip.domain.QTrip trip;

    public QTripRequest(String variable) {
        this(TripRequest.class, forVariable(variable), INITS);
    }

    public QTripRequest(Path<? extends TripRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTripRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTripRequest(PathMetadata metadata, PathInits inits) {
        this(TripRequest.class, metadata, inits);
    }

    public QTripRequest(Class<? extends TripRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.requester = inits.isInitialized("requester") ? new com.janggoback.tripwith.member.domain.QMember(forProperty("requester")) : null;
        this.trip = inits.isInitialized("trip") ? new com.janggoback.tripwith.trip.domain.QTrip(forProperty("trip"), inits.get("trip")) : null;
    }

}

