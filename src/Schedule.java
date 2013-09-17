import java.util.*;
public class Schedule {
  private float span(float fullLength, Break[] splits, int split) {
    // find the size of a given region between
    // breaks.
    if( splits.length == 0 ) {
      return fullLength;
    } else if( split == 0 ) {
      return splits[split].s;
    } else if(split >= splits.length) {
      return fullLength - splits[split-1].s - splits[split-1].l;
    } else {
      return splits[split].s - splits[split-1].s - splits[split-1].l;
    }
  };
  private float magnitude(ArrayList<Block> ps) {
    // find the sum of lengths of a period-array.
    float sum = 0;
    for( Block p : ps ) {
      sum += p.l;
    }
    return sum;
  }
  private float staticMag(ArrayList<Block> ps) {
    // find the sum of non-elastic period lengths.
    float sum = 0;
    for( Block p : ps ) {
      if( !p.e )
        sum += p.l;
    }
    return sum;
  };
  private float elasticMag(ArrayList<Block> ps) {
    // find the sum of elastic period lengths.
    float sum = 0;
    for( Block p : ps ) {
      if( p.e )
        sum += p.l;
    }
    return sum;
  };
  private ArrayList<Float> scale(ArrayList<Block> ps, float l) {
    // adjust a sequence of periods to fit
    // within a region length.
    float delta = l - magnitude(ps);
    ArrayList<Float> mapped = new ArrayList<Float>();
    for( Block p : ps ) {
      if( p.e && p.l + delta * p.l/elasticMag(ps) < 0 ) {
        throw new EmptyStackException();
      }
      mapped.add(p.e ? p.l + delta * p.l/elasticMag(ps) : p.l);
    }
    return mapped;
  };
  public Float[] reshape(Block[] blocks, Break[] breaks, float duration) {  
    // TODO: adjust lunch length if necessary.
    Block[] segments = blocks;
    float scroll = 0;
    int index = 0;
    int breakIndex = 0;
    ArrayList<Block> queue = new ArrayList<Block>();
    ArrayList<Float> sorted = new ArrayList<Float>();
    float fullLength = duration;
    while( index < segments.length ) {
      if( breakIndex >= breaks.length ) {
	// if no breaks remain, push the current period
	// to the queue unconditionally.
	queue.add(segments[index]);
      } else {      
	if( scroll + segments[index].l < breaks[breakIndex].s ) {
	  // if the current block fits into the current
	  // queue area (a region between breaks), add
	  // it to the queue.
	  queue.add(segments[index]);
	  scroll += segments[index].l;
	} else {
	  // otherwise, end the queue, and insert the break
	  sorted.addAll(scale(queue, span(fullLength, breaks, breakIndex)));
	  queue = new ArrayList<Block>();
	  sorted.add(breaks[breakIndex].l);
	  queue.add(segments[index]);
	  scroll = breaks[breakIndex].s + breaks[breakIndex].l;
	  scroll += segments[index].l;
	  breakIndex += 1;
	}
      }
      index += 1;
    }
    sorted.addAll(scale(queue, span(fullLength, breaks, breakIndex)));
    return sorted.toArray(new Float[sorted.size()]);
  }
}
