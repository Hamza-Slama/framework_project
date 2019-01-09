package tn.rnu.eniso.framework.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tn.rnu.eniso.framework.model.GenderEnum;
import tn.rnu.eniso.framework.model.UserType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T04:36:50")
@StaticMetamodel(ApplicationUsr.class)
public class ApplicationUsr_ { 

    public static volatile SingularAttribute<ApplicationUsr, String> firstName;
    public static volatile SingularAttribute<ApplicationUsr, String> lastName;
    public static volatile SingularAttribute<ApplicationUsr, Date> subscriptionDate;
    public static volatile SingularAttribute<ApplicationUsr, String> emailAddress;
    public static volatile SingularAttribute<ApplicationUsr, String> password;
    public static volatile SingularAttribute<ApplicationUsr, GenderEnum> gender;
    public static volatile SingularAttribute<ApplicationUsr, Long> id;
    public static volatile SingularAttribute<ApplicationUsr, String> login;
    public static volatile SingularAttribute<ApplicationUsr, UserType> type;
    public static volatile SingularAttribute<ApplicationUsr, Date> birthDate;

}