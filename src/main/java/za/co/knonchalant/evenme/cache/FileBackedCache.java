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
    public String get(String populateDetail, String title) throws IOException, InvalidCookieException {
        String contents = queryFromCache(title);
        if (contents != null) {
            LOG.debug("\"{}\" was already in cache", title);
            return contents;
        }

        contents = readFromUnderlyingSource(populateDetail);
        Files.write(determineFileForCache(title), contents.getBytes(StandardCharsets.UTF_8));
        LOG.info("Processed: \"{}\"", title);

        return contents;
    }

    @Override
    protected String readFromUnderlyingSource(String populateDetail) throws IOException, InvalidCookieException {
        return getCachePopulator().populate(populateDetail);
    }

    protected final String queryFromCache(String title) throws IOException {
        Path article = determineFileForCache(title);
        if (article.toFile().exists()) {
            return Files.readString(article);
        }
        return null;
    }

    protected final Path determineFileForCache(String title) {
        return cachePath.resolve(title);
    }
}
