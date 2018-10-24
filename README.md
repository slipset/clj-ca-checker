# clj-ca-checker

Ever needed to check if someone has signed the Clojure contributor agreement?

```
$ clojure -Sdeps '{:deps {clj-ca-checker {:git/url "https://github.com/slipset/clj-ca-checker" :sha "a0ea916eb606c048227f03f2c7d02ef851075f00"}}}' -m clj-ca-checker.clj-ca-checker <github username>
```

Maybe from your OSS, so only people with a signed CA can contribute?
Stick this in your `.circle/config.yml` as a `-run` step under `steps:`

```

      - run:
          name: Clojure CA Check
          command: |
            git_url='"https://github.com/slipset/clj-ca-checker"'
            sha='"a0ea916eb606c048227f03f2c7d02ef851075f00"'
            clojure -Sdeps "{:deps {clj-ca-checker {:git/url $git_url :sha $sha}}}" \
              -m clj-ca-checker.clj-ca-checker "$CIRCLE_PR_USERNAME"
```

Run the project directly:

    $ clj -m clj-ca-checker.clj-ca-checker <gitub username>


## License

Copyright © 2018 Erik Assum

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
