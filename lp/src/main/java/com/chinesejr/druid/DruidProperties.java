package com.chinesejr.druid;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 只提供了常用的属性，如果有需要，自己添加
 *
 * @since 2017/6/12.
 */
@ConfigurationProperties(prefix = "druid")
public class DruidProperties {
    private String connectionProperties;
    private String driverClass;
    private String filters;
    private int     initialSize;

    private int     maxActive;
    private int 	maxPoolPreparedStatementPerConnectionSize;
    private int 	maxWait;
    private int 	minEvictableIdleTimeMillis;
    private int     minIdle;
    private String password;
    private boolean poolPreparedStatements;

	private boolean testOnBorrow;

	private int		timeBetweenEvictionRunsMillis;

	private String url;
	
	private String username;

	public String getConnectionProperties() {
		return connectionProperties;
	}

	public String getDriverClass() {
        return driverClass;
    }

	public String getFilters() {
		return filters;
	}

	public int getInitialSize() {
        return initialSize;
    }

	public int getMaxActive() {
        return maxActive;
    }

	public int getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public int getMinIdle() {
        return minIdle;
    }
    public String getPassword() {
        return password;
    }
    public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
    public String getUrl() {
        return url;
    }

	public String getUsername() {
        return username;
    }

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setFilters(String filters) {
		this.filters = filters;
	}

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

    public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
