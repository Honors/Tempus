var Period = function(length, elastic) {
  this.l = length;
  this.e = elastic;
};
var Break = function(start, length) {
  this.s = start;
  this.l = length;
};

var span = function(fullLength, splits, split) {
  // find the size of a given region between
  // breaks.
  if( split == 0 ) {
    return splits[split].s;
  } else if(split >= splits.length) {
    return fullLength - splits[split-1].s - splits[split-1].l;
  } else {
    return splits[split].s - splits[split-1].s - splits[split-1].l;
  }
};
var magnitude = function(ps) {
  // find the sum of lengths of a period-array.
  return ps.map(function(p) {
    return p.l;
  }).reduce(function(a, b) {
    return a+b;
  });
};
var staticMag = function(ps) {
  // find the sum of non-elastic period lengths.
  return ps.filter(function(p) {
    return !p.e;
  }).map(function(p) {
    return p.l;
  }).reduce(function(a, b) {
    return a+b;
  });
};
var elasticMag = function(ps) {
  // find the sum of elastic period lengths.
  return ps.filter(function(p) {
    return p.e;
  }).map(function(p) {
    return p.l;
  }).reduce(function(a, b) {
    return a+b;
  });
};
var scale = function(ps, l) {
  // adjust a sequence of periods to fit
  // within a region length.
  if( ps.length == 1 ) {
    return l;
  }
  var delta = l - magnitude(ps);
  return ps.map(function(p) {
    if( p.e && p.l + delta * p.l/elasticMag(ps) < 0 ) {
      throw "Unable to fit into a fixed range. Scaling forced negative block size.";
    }
    return p.e ? p.l + delta * p.l/elasticMag(ps) : p.l;
  });
};
var reshape = function(blocks, breaks, duration) {  
  var segments = blocks;
  var scroll = 0,
      index = 0,
      breakIndex = 0,
      queue = [],
      sorted = [],
      fullLength = duration;
  while( index < segments.length ) {
    if( !breaks[breakIndex] ) {
      // if no breaks remain, push the current period
      // to the queue unconditionally.
      queue.push(segments[index]);
    } else {      
      if( scroll + segments[index].l < breaks[breakIndex].s ) {
        // if the current block fits into the current
	// queue area (a region between breaks), add
	// it to the queue.
	queue.push(segments[index]);
	scroll += segments[index].l;
      } else {
        // otherwise, end the queue, and insert the break
	sorted = sorted.concat(scale(queue, span(fullLength, breaks, breakIndex)));
	queue = [];
	sorted.push(breaks[breakIndex].l);
	queue.push(segments[index]);
	scroll = breaks[breakIndex].s + breaks[breakIndex].l;
	scroll += segments[index].l;
	breakIndex += 1;
      }
    }
    index += 1;
  }
  sorted = sorted.concat(scale(queue, span(fullLength, breaks, breakIndex)));
  return sorted;
};

// form a list of blocks and breaks and form
// a schedule of them to fit a given duration.

// TODO: adjust lunch length if necessary.

var n = new Period(10, true);
var l = new Period(15, false);
var s = new Period(5, false);
var segments = [n, s, n, s, n, s, l, s, n, s, n, s, n];
var breaks = [new Break(42, 10), new Break(68, 10)];
var sorted = reshape(segments, breaks, 125);
console.log(sorted.join("\n"));
