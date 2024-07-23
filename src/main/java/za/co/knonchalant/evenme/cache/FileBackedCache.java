package za.co.knonchalant.evenme.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.knonchalant.evenme.scrape.news24.InvalidCookieException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBackedCache extends Cache {
    private static final Logger LOG = LoggerFactory.getLogger(FileBackedCache.class);

    protected final Path cachePath;

    public FileBackedCache(Path cachePath, CachePopulator cachePopulator) {
        super(cachePopulator);
        this.cachePath = cachePath;
    }

    @Override
    public CacheRecord get(String populateDetail, String title) throws IOException, InvalidCookieException {
        CacheRecord record = queryFromCache(title);
        if (record == null) {
            record = readFromUnderlyingSource(populateDetail);
            Files.write(determineFileForCache(title), record.getResult().getBytes(StandardCharsets.UTF_8));
            LOG.info("Cached: \"{}\"", title);
        } else {
            LOG.debug("Already in cache:- \"{}\"", title);
        }

        return record;
    }

    @Override
    protected final CacheRecord readFromUnderlyingSource(String populateDetail) throws IOException, InvalidCookieException {
        return new CacheRecord(getCachePopulator().populate(populateDetail), false);
    }

    protected final CacheRecord queryFromCache(String title) throws IOException {
        Path article = determineFileForCache(title);
        if (article.toFile().exists()) {
            return new CacheRecord(Files.readString(article), true);
        }
        return null;
    }

    protected final Path determineFileForCache(String title) {
        return cachePath.resolve(title);
    }
}
