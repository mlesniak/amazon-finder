# Amazon product finder

The idea was to develop a better product finder than the one provided by amazon. For instance, you should be able to

- filter for minimum and maximum prices
- view all reviews in full
- sort reviews by some metrics
- reliably filter non-amazon shops
- ...and a lot of other good ideas.

# ...but
According to amazons' license agreement a developer is not allowed to
alter the returned values, i.e. modify the displayed customer reviews for
aggregation, etc.

Hence, the main purpose of this project is somewhat nullified and I
decide to stop further development.

Note that the code is far from finished, e.g.

- logging
- exception handling
- configuration by file instead of constants (e.g. credentials)

are missing but were planned after a working proof of concept (which I had even for customer reviews, but decided to not
commit it).
