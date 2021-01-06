module ISSstationSDAproject {
	requires okhttp3;
	requires java.persistence;
	requires com.fasterxml.jackson.databind;
	requires java.desktop;
	requires org.hibernate.orm.core;
	requires java.naming;
	requires net.bytebuddy;
	opens ISS.functionality.managers;
}