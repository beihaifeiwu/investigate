package com.freetmp.investigate.persistence.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMseService is a Querydsl query drinkType for MseService
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMseService extends com.mysema.query.sql.RelationalPathBase<MseService> {

    private static final long serialVersionUID = -408423967;

    public static final QMseService mseService = new QMseService("mse_service");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath url = createString("url");

    public final StringPath username = createString("username");

    public final com.mysema.query.sql.PrimaryKey<MseService> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<MsePlanarGraph> _qs4u2kipsf0pr0cgtpbha14e9FK = createInvForeignKey(id, "service_id");

    public QMseService(String variable) {
        super(MseService.class, forVariable(variable), "null", "mse_service");
        addMetadata();
    }

    public QMseService(String variable, String schema, String table) {
        super(MseService.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMseService(Path<? extends MseService> path) {
        super(path.getType(), path.getMetadata(), "null", "mse_service");
        addMetadata();
    }

    public QMseService(PathMetadata<?> metadata) {
        super(MseService.class, metadata, "null", "mse_service");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(password, ColumnMetadata.named("password").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(url, ColumnMetadata.named("url").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(username, ColumnMetadata.named("username").withIndex(4).ofType(Types.VARCHAR).withSize(255));
    }

}

