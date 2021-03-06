package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.ludumDare_DT.ludumDare_DT_2015.profiling.Profiler;
import de.ludumDare_DT.ludumDare_DT_2015.profiling.Profiling;

public abstract class IteratingProfilingSystem extends IteratingSystem implements Profiling{

	private boolean doProfiling;
	Profiler profiler;
	
	public IteratingProfilingSystem(Family family) {
		this(family, 0);
		
	}
	
	public IteratingProfilingSystem(Family family, int priority){
		super(family, priority);
		profiler = new Profiler();
	}
	
	@Override
	public void update(float deltaTime) {
		if(doProfiling){
			profiler.startTime();
		}
		super.update(deltaTime);
		
		if(doProfiling){
			profiler.endTime();
			profiler.outMax();
		}
	}
	
	@Override
	public void setProfiling(boolean profiling) {
		doProfiling = profiling;
		
	}

}
