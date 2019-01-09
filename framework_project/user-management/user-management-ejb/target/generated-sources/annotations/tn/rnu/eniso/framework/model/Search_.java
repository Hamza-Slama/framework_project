package tn.rnu.eniso.framework.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tn.rnu.eniso.framework.model.ApplicationUsr;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T04:36:50")
@StaticMetamodel(Search.class)
public class Search_ { 

    public static volatile SingularAttribute<Search, String> field;
    public static volatile SingularAttribute<Search, Date> searchDate;
    public static volatile SingularAttribute<Search, Long> id;
    public static volatile SingularAttribute<Search, ApplicationUsr> user;

}