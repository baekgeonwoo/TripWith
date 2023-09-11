package com.janggoback.tripwith.member.response;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberResponse is a Querydsl query type for MemberResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberResponse extends EntityPathBase<MemberResponse> {

    private static final long serialVersionUID = 1972526229L;

    public static final QMemberResponse memberResponse = new QMemberResponse("memberResponse");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.janggoback.tripwith.member.domain.Gender> gender = createEnum("gender", com.janggoback.tripwith.member.domain.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath region = createString("region");

    public QMemberResponse(String variable) {
        super(MemberResponse.class, forVariable(variable));
    }

    public QMemberResponse(Path<? extends MemberResponse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberResponse(PathMetadata metadata) {
        super(MemberResponse.class, metadata);
    }

}

