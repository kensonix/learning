package chapter14.typeinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part{
	public String toString(){
		return getClass().getSimpleName();
	}
	
	static List<Factory<? extends Part>> partFactories = 
			new ArrayList<Factory<? extends Part>>();
	
	static {
		partFactories.add(new FuelFilter.Factory());
		partFactories.add(new AirFilter.Factory());
		partFactories.add(new CabinAirFilter.Factory());
		partFactories.add(new OilFilter.Factory());
		partFactories.add(new FanBelt.Factory());
		partFactories.add(new PowerSteeringBelt.Factory());
		partFactories.add(new GeneratorBelt.Factory());
	}
	
	private static Random random = new Random(47);
	public static Part createRandom(){
		int n  = random.nextInt(partFactories.size());
		return partFactories.get(n).create();
	}
}
class Filter extends Part{}

class FuelFilter extends Filter{
	public static class Factory implements chapter14.typeinfo.Factory<FuelFilter>{
		public FuelFilter create(){
			return new FuelFilter();
		}
	}
}

class AirFilter extends Filter{
	public static class Factory 
	implements  chapter14.typeinfo.Factory<AirFilter>{
		public AirFilter create(){ return new AirFilter();}
	}
}

class CabinAirFilter extends Filter{
	public static class Factory 
	implements chapter14.typeinfo.Factory<CabinAirFilter>{
		public CabinAirFilter create(){
			return new CabinAirFilter();
		}
	}
	
}

class OilFilter extends Filter{
	public static class Factory 
	implements chapter14.typeinfo.Factory<OilFilter>{
		public OilFilter create(){
			return new OilFilter();
		}
	}
}

class Belt extends Part{}

class FanBelt extends Part{
	public static class Factory 
	implements chapter14.typeinfo.Factory<FanBelt>{
		public FanBelt create(){
			return new FanBelt();
		}
	}
}

class GeneratorBelt extends Belt{
	public static class Factory 
	implements chapter14.typeinfo.Factory<GeneratorBelt>{
		public GeneratorBelt create(){
			return new GeneratorBelt();
		}
	}
}

class PowerSteeringBelt extends Belt{
	public static class Factory
	 implements chapter14.typeinfo.Factory<PowerSteeringBelt>{
		public PowerSteeringBelt create(){
			return new PowerSteeringBelt();
		}
	}
}

public class RegisterFactoies {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			System.out.println(Part.createRandom());
		}
	}
}



