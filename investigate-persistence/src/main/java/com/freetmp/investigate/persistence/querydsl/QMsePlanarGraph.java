package com.freetmp.investigate.persistence.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMsePlanarGraph is a Querydsl query drinkType for MsePlanarGraph
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMsePlanarGraph extends com.mysema.query.sql.RelationalPathBase<MsePlanarGraph> {

    private static final long serialVersionUID = 1432270208;

    public static final QMsePlanarGraph msePlanarGraph = new QMsePlanarGraph("mse_planar_graph");

    public final BooleanPath durable = createBoolean("durable");

    public final NumberPath<Long> floorRefId = createNumber("floorRefId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> originX = createNumber("originX", Double.class);

    public final NumberPath<Double> originY = createNumber("originY", Double.class);

    public final NumberPath<Long> planarGraphId = createNumber("planarGraphId", Long.class);

    public final NumberPath<Integer> serviceId = createNumber("serviceId", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<MsePlanarGraph> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<MseService> qs4u2kipsf0pr0cgtpbha14e9FK = createForeignKey(serviceId, "id");

    public QMsePlanarGraph(String variable) {
        super(MsePlanarGraph.class, forVariable(variable), "null", "mse_planar_graph");
        addMetadata();
    }

    public QMsePlanarGraph(String variable, String schema, String table) {
        super(MsePlanarGraph.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMsePlanarGraph(Path<? extends MsePlanarGraph> path) {
        super(path.getType(), path.getMetadata(), "null", "mse_planar_graph");
        addMetadata();
    }

    public QMsePlanarGraph(PathMetadata<?> metadata) {
        super(MsePlanarGraph.class, metadata, "null", "mse_planar_graph");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(durable, ColumnMetadata.named("durable").withIndex(2).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(floorRefId, ColumnMetadata.named("floor_ref_id").withIndex(3).ofType(Types.BIGINT).withSize(19));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(originX, ColumnMetadata.named("origin_x").withIndex(4).ofType(Types.DOUBLE).withSize(22));
        addMetadata(originY, ColumnMetadata.named("origin_y").withIndex(5).ofType(Types.DOUBLE).withSize(22));
        addMetadata(planarGraphId, ColumnMetadata.named("planar_graph_id").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(serviceId, ColumnMetadata.named("service_id").withIndex(7).ofType(Types.INTEGER).withSize(10));
    }

}

