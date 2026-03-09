package QuantityMeasurementApp.com.QuantityMeasurementApp;
import java.util.Objects;
public class Length {
	private final double val;
	private final LengthUnit unit;
	public enum LengthUnit{
		FEET (12.0),
		INCHES(1.0),
		YARDS(36.0),
		CENTIMETERS(0.393701);
		private final double conversionFactor;
		
		LengthUnit (double conversionFactor){
			this.conversionFactor = conversionFactor;
			
		}
		public double getConversionFactor() {
			return conversionFactor;
		}
		
		
	}
	public Length (double val , LengthUnit unit) {
		if(unit==null) throw new IllegalArgumentException("Null Unit is not acceptable.");
		
		this.val=val;
		this.unit = unit;
		
	}
	private double toBaseUnit() {
		return val*unit.getConversionFactor();
	}
	
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(!(obj instanceof Length)) return false;
		
		Length curr = (Length)obj;
//		return Double.compare(this.toBaseUnit(), that.toBaseUnit()) == 0;
		return Double.compare(this.toBaseUnit() ,curr.toBaseUnit())==0;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(toBaseUnit());
	}
	
	@Override
	public String toString() {
		return val + " " + unit.name().toLowerCase();
	}
	
	public static double convert(double value,LengthUnit source,LengthUnit target) {
		
		if (source == null || target == null) {			
			throw new IllegalArgumentException("Units cannot be null");
		}
		
		if (!Double.isFinite(value)) {
			
			throw new IllegalArgumentException("Value must be finite");
		}
		
		double base =value*source.getConversionFactor();
		return base/target.getConversionFactor();
	}
	public Length convertTo(LengthUnit target) {
        double newValue =convert(this.val, this.unit,target);
        return new Length(newValue,target);
    }
	
	 public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (!Double.isFinite(this.val) || !Double.isFinite(other.val))
            throw new IllegalArgumentException("Values must be finite numbers");

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double sumBase = base1 + base2;

        double resultValue = sumBase / this.unit.getConversionFactor();

        return new Length(resultValue, this.unit);
    }
	
}
