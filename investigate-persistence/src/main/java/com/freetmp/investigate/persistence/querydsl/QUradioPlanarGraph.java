package com.freetmp.investigate.persistence.querydsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUradioPlanarGraph is a Querydsl query drinkType for UradioPlanarGraph
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUradioPlanarGraph extends com.mysema.query.sql.RelationalPathBase<UradioPlanarGraph> {

    private static final long serialVersionUID = -1194421821;

    public static final QUradioPlanarGraph uradioPlanarGraph = new QUradioPlanarGraph("uradio_planar_graph");

    public final NumberPath<Double> deltaX = createNumber("deltaX", Double.class);

    public final NumberPath<Double> deltaY = createNumber("deltaY", Double.class);

    public final NumberPath<Integer> dpi = createNumber("dpi", Integer.class);

    public final BooleanPath durable = createBoolean("durable");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> mapHeight = createNumber("mapHeight", Integer.class);

    public final NumberPath<Long> mapId = createNumber("mapId", Long.class);

    public final NumberPath<Integer> mapWidth = createNumber("mapWidth", Integer.class);

    public final NumberPath<Double> originX = createNumber("originX", Double.class);

    public final NumberPath<Double> originY = createNumber("originY", Double.class);

    public final NumberPath<Float> scale = createNumber("scale", Float.class);

    public final NumberPath<Float> uradioScale = createNumber("uradioScale", Float.class);

    public final com.mysema.query.sql.PrimaryKey<UradioPlanarGraph> primary = createPrimaryKey(id);

    public QUradioPlanarGraph(String variable) {
        super(UradioPlanarGraph.class, forVariable(variable), "null", "uradio_planar_graph");
        addMetadata();
    }

    public QUradioPlanarGraph(String variable, String schema, String table) {
        super(UradioPlanarGraph.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUradioPlanarGraph(Path<? extends UradioPlanarGraph> path) {
        super(path.getType(), path.getMetadata(), "null", "uradio_planar_graph");
        addMetadata();
    }

    public QUradioPlanarGraph(PathMetadata<?> metadata) {
        super(UradioPlanarGraph.class, metadata, "null", "uradio_planar_graph");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(deltaX, ColumnMetadata.named("delta_x").withIndex(2).ofType(Types.DOUBLE).withSize(22));
        addMetadata(deltaY, ColumnMetadata.named("delta_y").withIndex(3).ofType(Types.DOUBLE).withSize(22));
        addMetadata(dpi, ColumnMetadata.named("dpi").withIndex(4).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(durable, ColumnMetadata.named("durable").withIndex(5).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(mapHeight, ColumnMetadata.named("map_height").withIndex(6).ofType(Types.INTEGER).withSize(10));
        addMetadata(mapId, ColumnMetadata.named("map_id").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(mapWidth, ColumnMetadata.named("map_width").withIndex(8).ofType(Types.INTEGER).withSize(10));
        addMetadata(originX, ColumnMetadata.named("origin_x").withIndex(9).ofType(Types.DOUBLE).withSize(22));
        addMetadata(originY, ColumnMetadata.named("origin_y").withIndex(10).ofType(Types.DOUBLE).withSize(22));
        addMetadata(scale, ColumnMetadata.named("scale").withIndex(11).ofType(Types.REAL).withSize(12).notNull());
        addMetadata(uradioScale, ColumnMetadata.named("uradio_scale").withIndex(12).ofType(Types.REAL).withSize(12));
    }

}

