package com.freetmp.investigate.persistence.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUradioService is a Querydsl query drinkType for UradioService
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUradioService extends com.mysema.query.sql.RelationalPathBase<UradioService> {

    private static final long serialVersionUID = 983459748;

    public static final QUradioService uradioService = new QUradioService("uradio_service");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath prefix = createString("prefix");

    public final StringPath url = createString("url");

    public final com.mysema.query.sql.PrimaryKey<UradioService> primary = createPrimaryKey(id);

    public QUradioService(String variable) {
        super(UradioService.class, forVariable(variable), "null", "uradio_service");
        addMetadata();
    }

    public QUradioService(String variable, String schema, String table) {
        super(UradioService.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUradioService(Path<? extends UradioService> path) {
        super(path.getType(), path.getMetadata(), "null", "uradio_service");
        addMetadata();
    }

    public QUradioService(PathMetadata<?> metadata) {
        super(UradioService.class, metadata, "null", "uradio_service");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(prefix, ColumnMetadata.named("prefix").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(url, ColumnMetadata.named("url").withIndex(4).ofType(Types.VARCHAR).withSize(255));
    }

}

