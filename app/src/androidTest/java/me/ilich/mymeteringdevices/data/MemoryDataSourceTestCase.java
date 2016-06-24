package me.ilich.mymeteringdevices.data;

public class MemoryDataSourceTestCase extends DataSourceTestCase {

    @Override
    protected DataSource createDataSource() {
        return new MemoryDataSource();
    }

}
