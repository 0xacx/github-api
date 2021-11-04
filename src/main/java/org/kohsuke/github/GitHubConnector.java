package org.kohsuke.github;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Pluggability for customizing HTTP request behaviors or using altogether different library.
 *
 * <p>
 * For example, you can implement this to st custom timeouts.
 *
 * @author Kohsuke Kawaguchi
 */
@FunctionalInterface
public interface GitHubConnector extends HttpConnector {
    /**
     * Opens a connection to the given URL.
     *
     * @param url
     *            the url
     * @return the http url connection
     * @throws IOException
     *             the io exception
     */
    @Deprecated
    default HttpURLConnection connect(URL url) throws IOException {
        throw new UnsupportedOperationException();
    }

    GitHubResponse.ResponseInfo send(GitHubRequest request) throws IOException;

    /**
     * Default implementation that uses {@link URL#openConnection()}.
     */
    GitHubConnector DEFAULT = new GitHubConnectorHttpConnectorAdapter(HttpConnector.DEFAULT);

    GitHubConnector OFFLINE = new GitHubConnectorHttpConnectorAdapter(HttpConnector.OFFLINE);
}
