package com.freetmp.investigate.persistence.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QEtonePlanarGraph is a Querydsl query drinkType for EtonePlanarGraph
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QEtonePlanarGraph extends com.mysema.query.sql.RelationalPathBase<EtonePlanarGraph> {

    private static final long serialVersionUID = 1219098600;

    public static final QEtonePlanarGraph etonePlanarGraph = new QEtonePlanarGraph("etone_planar_graph");

    public final BooleanPath durable = createBoolean("durable");

    public final StringPath floor = createString("floor");

    public final NumberPath<Double> originX = createNumber("originX", Double.class);

    public final NumberPath<Double> originY = createNumber("originY", Double.class);

    public final NumberPath<Long> planarGraphId = createNumber("planarGraphId", Long.class);

    public final com.mysema.query.sql.PrimaryKey<EtonePlanarGraph> primary = createPrimaryKey(floor);

    public QEtonePlanarGraph(String variable) {
        super(EtonePlanarGraph.class, forVariable(variable), "null", "etone_planar_graph");
        addMetadata();
    }

    public QEtonePlanarGraph(String variable, String schema, String table) {
        super(EtonePlanarGraph.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QEtonePlanarGraph(Path<? extends EtonePlanarGraph> path) {
        super(path.getType(), path.getMetadata(), "null", "etone_planar_graph");
        addMetadata();
    }

    public QEtonePlanarGraph(PathMetadata<?> metadata) {
        super(EtonePlanarGraph.class, metadata, "null", "etone_planar_graph");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(durable, ColumnMetadata.named("durable").withIndex(2).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(floor, ColumnMetadata.named("floor").withIndex(1).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(originX, ColumnMetadata.named("origin_x").withIndex(3).ofType(Types.DOUBLE).withSize(22).notNull());
        addMetadata(originY, ColumnMetadata.named("origin_y").withIndex(4).ofType(Types.DOUBLE).withSize(22).notNull());
        addMetadata(planarGraphId, ColumnMetadata.named("planar_graph_id").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

