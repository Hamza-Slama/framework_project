package tn.rnu.eniso.framework.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tn.rnu.eniso.framework.model.ApplicationUsr;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T04:36:50")
@StaticMetamodel(Preference.class)
public class Preference_ { 

    public static volatile SingularAttribute<Preference, String> memory;
    public static volatile SingularAttribute<Preference, String> GraphicCard;
    public static volatile SingularAttribute<Preference, Date> preferenceDate;
    public static volatile SingularAttribute<Preference, String> hardDisk;
    public static volatile SingularAttribute<Preference, String> screen;
    public static volatile SingularAttribute<Preference, String> linkToImage;
    public static volatile SingularAttribute<Preference, String> processor;
    public static volatile SingularAttribute<Preference, String> productName;
    public static volatile SingularAttribute<Preference, String> linkToDetails;
    public static volatile SingularAttribute<Preference, String> price;
    public static volatile SingularAttribute<Preference, Integer> rank;
    public static volatile SingularAttribute<Preference, Long> id;
    public static volatile SingularAttribute<Preference, String> frequence;
    public static volatile SingularAttribute<Preference, ApplicationUsr> user;

}