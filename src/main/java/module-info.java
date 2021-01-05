module ISSstationSDAproject {
	requires okhttp3;
	requires java.persistence;
	requires com.fasterxml.jackson.databind;
	requires java.desktop;
	requires org.hibernate.orm.core;
	requires java.naming;
	requires net.bytebuddy;
	exports ISS.database.numberofastronauts.entity;
	exports ISS.database.position.entity;
	exports ISS.functionality.isspasses;
	opens ISS.database.position.entity;
	opens ISS.database.numberofastronauts.entity;
	opens ISS.functionality.isspasses;
}