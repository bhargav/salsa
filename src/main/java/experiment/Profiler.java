package demo;

// Import declarations generated by the SALSA compiler, do not modify.
import java.io.IOException;
import java.util.Vector;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

import salsa.language.Actor;
import salsa.language.ActorReference;
import salsa.language.Message;
import salsa.language.RunTime;
import salsa.language.ServiceFactory;
import gc.WeakReference;
import salsa.language.Token;
import salsa.language.exceptions.*;
import salsa.language.exceptions.CurrentContinuationException;

import salsa.language.UniversalActor;

import salsa.naming.UAN;
import salsa.naming.UAL;
import salsa.naming.MalformedUALException;
import salsa.naming.MalformedUANException;

import salsa.resources.SystemService;

import salsa.resources.ActorService;

// End SALSA compiler generated import delcarations.


public class Profiler extends UniversalActor  implements ActorService {
	public static void main(String args[]) {
		UAN uan = null;
		UAL ual = null;
		if (System.getProperty("uan") != null) {
			uan = new UAN( System.getProperty("uan") );
			ServiceFactory.getTheater();
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("ual") != null) {
			ual = new UAL( System.getProperty("ual") );

			if (uan == null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an actor to have a ual at runtime without a uan.");
				System.err.println("	To give an actor a specific ual at runtime, use the identifier system property.");
				System.exit(0);
			}
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("identifier") != null) {
			if (ual != null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an identifier and a ual with system properties when creating an actor.");
				System.exit(0);
			}
			ual = new UAL( ServiceFactory.getTheater().getLocation() + System.getProperty("identifier"));
		}
		Profiler instance = (Profiler)new Profiler(uan, ual,null).construct();
		{
			Object[] _arguments = { args };
			instance.send( new Message(instance, instance, "act", _arguments, null, null) );
		}
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new Profiler(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return Profiler.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new Profiler(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return Profiler.getReferenceByLocation(new UAL(ual)); }
	public Profiler(boolean o, UAN __uan)	{ super(false,__uan); }
	public Profiler(boolean o, UAL __ual)	{ super(false,__ual); }

	public Profiler(UAN __uan,UniversalActor.State sourceActor)	{ this(__uan, null,null); }
	public Profiler(UAL __ual,UniversalActor.State sourceActor)	{ this(null, __ual,null); }
	public Profiler(UniversalActor.State sourceActor)		{ this(null, null,null);  }
	public Profiler()		{  }
	public Profiler(UAN __uan, UAL __ual,Object sourceActor) {
		if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {
			createRemotely(__uan, __ual, "demo.Profiler");
		} else {
			State state = new State(__uan, __ual);
			state.updateSelf(this);
			ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(), state);
			if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
		}
	}

	public UniversalActor construct (boolean is_reporting, Reporter_Actor rep_actor) {
		Object[] __arguments = { new Boolean(is_reporting), rep_actor };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public UniversalActor construct() {
		Object[] __arguments = { };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public class State extends UniversalActor .State implements salsa.resources.ActorServiceState {
		public Profiler self;
		public void updateSelf(ActorReference actorReference) {
			((Profiler)actorReference).setUAL(getUAL());
			((Profiler)actorReference).setUAN(getUAN());
			self = new Profiler(false,getUAL());
			self.setUAN(getUAN());
			self.setUAL(getUAL());
			self.muteGC();
		}

		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "demo.Profiler$State" );
			addMethodsForClasses();
		}

		public void construct() {}

		public void process(Message message) {
			Method[] matches = getMatches(message.getMethodName());
			Object returnValue = null;
			Exception exception = null;

			if (matches != null) {
				if (!message.getMethodName().equals("die")) {activateArgsGC(message);}
				for (int i = 0; i < matches.length; i++) {
					try {
						if (matches[i].getParameterTypes().length != message.getArguments().length) continue;
						returnValue = matches[i].invoke(this, message.getArguments());
					} catch (Exception e) {
						if (e.getCause() instanceof CurrentContinuationException) {
							sendGeneratedMessages();
							return;
						} else if (e instanceof InvocationTargetException) {
							sendGeneratedMessages();
							exception = (Exception)e.getCause();
							break;
						} else {
							continue;
						}
					}
					sendGeneratedMessages();
					currentMessage.resolveContinuations(returnValue);
					return;
				}
			}

			System.err.println("Message processing exception:");
			if (message.getSource() != null) {
				System.err.println("\tSent by: " + message.getSource().toString());
			} else System.err.println("\tSent by: unknown");
			System.err.println("\tReceived by actor: " + toString());
			System.err.println("\tMessage: " + message.toString());
			if (exception == null) {
				if (matches == null) {
					System.err.println("\tNo methods with the same name found.");
					return;
				}
				System.err.println("\tDid not match any of the following: ");
				for (int i = 0; i < matches.length; i++) {
					System.err.print("\t\tMethod: " + matches[i].getName() + "( ");
					Class[] parTypes = matches[i].getParameterTypes();
					for (int j = 0; j < parTypes.length; j++) {
						System.err.print(parTypes[j].getName() + " ");
					}
					System.err.println(")");
				}
			} else {
				System.err.println("\tThrew exception: " + exception);
				exception.printStackTrace();
			}
		}

		long serialVersionUID = 2L;
		Reporter_Actor reporter_actor;
		boolean isReporting;
		public void construct(boolean is_reporting, Reporter_Actor rep_actor){
			reporter_actor = (Reporter_Actor)rep_actor;
			isReporting = is_reporting;
		}
		public void start_battery_profiling() {
			{
				// standardOutput<-println("Profiler: Battery Profiler started.")
				{
					Object _arguments[] = { "Profiler: Battery Profiler started." };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			this.report("Profiler: Battery Profiler started.");
			{
				// standardOutput<-registerBatteryStatusReceiver(((Profiler)self))
				{
					Object _arguments[] = { ((Profiler)self) };
					Message message = new Message( self, standardOutput, "registerBatteryStatusReceiver", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		public void start_energy_profiling() {
			{
				// standardOutput<-println("Profiler: Energy Profiler started.")
				{
					Object _arguments[] = { "Profiler: Energy Profiler started." };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			this.report("Profiler: Energy Profiler started.");
			{
				// standardOutput<-registerEnergyProfilerReceiver(((Profiler)self))
				{
					Object _arguments[] = { ((Profiler)self) };
					Message message = new Message( self, standardOutput, "registerEnergyProfilerReceiver", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		public void battery_status_update(boolean isCharging, boolean usbCharge, boolean acCharge, int batteryLevel, int voltage, int temp) {
			long startTime = System.currentTimeMillis();
			{
				// standardOutput<-println("Profiler: batteryLevel="+batteryLevel+" voltage="+voltage+" temp="+temp+" time:"+startTime)
				{
					Object _arguments[] = { "Profiler: batteryLevel="+batteryLevel+" voltage="+voltage+" temp="+temp+" time:"+startTime };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			this.report("Profiler: batteryLevel="+batteryLevel+" voltage="+voltage+" temp="+temp+" time:"+startTime);
		}
		public void energy_status_update(String reporting_title, String reporting_time_interval, double battery_temp, double battery_voltage, double battery_charge, double battery_perc, double instant_power, double avg_power, double current, double total_energy, String[] apps_name, double[] apps_energy_perc, long[] apps_duration, double[] apps_energy_value) {
			long startTime = System.currentTimeMillis();
			{
				// standardOutput<-println("Profiler: Energy Update: battery_voltage="+battery_voltage+" instant power="+instant_power+" time:"+startTime)
				{
					Object _arguments[] = { "Profiler: Energy Update: battery_voltage="+battery_voltage+" instant power="+instant_power+" time:"+startTime };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			this.report("Profiler: Energy Update: battery_voltage="+battery_voltage+" instant power="+instant_power+" time:"+startTime);
		}
		public void report(String msg) {
			if (isReporting) {			{
				// reporter_actor<-report(msg)
				{
					Object _arguments[] = { msg };
					Message message = new Message( self, reporter_actor, "report", _arguments, null, null );
					__messages.add( message );
				}
			}
}		}
	}
}