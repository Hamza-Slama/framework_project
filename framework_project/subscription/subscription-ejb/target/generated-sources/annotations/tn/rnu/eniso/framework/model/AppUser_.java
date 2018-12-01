package tn.rnu.eniso.framework.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ {

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

