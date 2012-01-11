package ru.mai.neuro.kurs;

import java.awt.geom.Point2D;

/**
 * Created by IntelliJ IDEA.
* User: and_hom
* Date: 17.01.2008
* Time: 18:19:32
* To change this template use File | Settings | File Templates.
*/
public class ExperimentalResult {

    private Point2D.Double point;
    private Boolean fromFirstCollection;

    public ExperimentalResult() {
    }


    public ExperimentalResult(Point2D.Double point, Boolean fromFirstCollection) {
        this.point = point;
        this.fromFirstCollection = fromFirstCollection;
    }

    public Boolean isFromFirstCollection() {
        return fromFirstCollection;
    }

    public void setFromFirstCollection(Boolean fromFirstCollection) {
        this.fromFirstCollection = fromFirstCollection;
    }

    public Point2D.Double getPoint() {
        return point;
    }

    public void setPoint(Point2D.Double point) {
        this.point = point;
    }


}
