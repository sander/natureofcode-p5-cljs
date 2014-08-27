goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.object', 'goog.string.StringBuffer', 'goog.array']);
goog.addDependency("../natureofcode/pvector.js", ['natureofcode.pvector'], ['cljs.core']);
goog.addDependency("../natureofcode/core.js", ['natureofcode.core'], ['cljs.core']);
goog.addDependency("../natureofcode/one.js", ['natureofcode.one'], ['natureofcode.core', 'cljs.core', 'natureofcode.pvector']);
goog.addDependency("../natureofcode/zero.js", ['natureofcode.zero'], ['natureofcode.core', 'cljs.core']);
goog.addDependency("../natureofcode/ecosystem.js", ['natureofcode.ecosystem'], ['natureofcode.core', 'cljs.core', 'natureofcode.pvector']);