'use strict';

(function (global) {

  var commons = {};
  var salt = '$s1KL(3?,De5L-zx39!%.aPf~9Z';
  var sha1 = new Hashes.SHA1();

  commons.encodePassword = function (username, rawPassword) {
    return sha1.hex(username + rawPassword + salt);
  };

  global.commons = commons;

})(window);
