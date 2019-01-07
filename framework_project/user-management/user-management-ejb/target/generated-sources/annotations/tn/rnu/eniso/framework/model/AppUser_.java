package tn.rnu.eniso.framework.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tn.rnu.eniso.framework.model.GenderEnum;
import tn.rnu.eniso.framework.model.UserType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-05T15:11:07")
@StaticMetamodel(AppUser.class)
public class AppUser_ { 

    public static volatile SingularAttribute<AppUser, String> firstName;
    public static volatile SingularAttribute<AppUser, String> lastName;
    public static volatile SingularAttribute<AppUser, Date> subscriptionDate;
    public static volatile SingularAttribute<AppUser, String> emailAddress;
    public static volatile SingularAttribute<AppUser, String> password;
    public static volatile SingularAttribute<AppUser, GenderEnum> gender;
    public static volatile SingularAttribute<AppUser, Long> id;
    public static volatile SingularAttribute<AppUser, String> login;
    public static volatile SingularAttribute<AppUser, UserType> type;
    public static volatile SingularAttribute<AppUser, Date> birthDate;

}