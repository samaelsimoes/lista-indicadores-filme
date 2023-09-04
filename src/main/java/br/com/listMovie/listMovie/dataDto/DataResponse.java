package br.com.listMovie.listMovie.dataDto;

import java.util.List;
import java.util.ArrayList;

public class DataResponse {

    private List<ProducerInterval> min;

    private List<ProducerInterval> max;

    public DataResponse() {
    }

    public DataResponse(List<ProducerInterval> min, List<ProducerInterval> max) {
        this.min = min;
        this.max = max;
    }

    public void addMin(ProducerInterval producerInterval) {
        if (this.min == null) {
            this.min = new ArrayList<>();
        }
        if (min.isEmpty()) {
            this.min.add(producerInterval);
        } else if (producerInterval.getInterval() < this.min.get(0).getInterval()) {
            this.min.clear();
            this.min.add(producerInterval);
        } else if (producerInterval.getInterval() == this.min.get(0).getInterval()) {
            this.min.add(producerInterval);
        }
    }

    public void addMax(ProducerInterval producerInterval) {
        if (this.max == null) {
            this.max = new ArrayList<>();
        }
        if (max.isEmpty()) {
            this.max.add(producerInterval);
        } else if (producerInterval.getInterval() > this.max.get(0).getInterval()) {
            this.max.clear();
            this.max.add(producerInterval);
        } else if (producerInterval.getInterval() == this.max.get(0).getInterval()) {
            this.max.add(producerInterval);
        }
    }

    public List<ProducerInterval> getMin() {
        return min;
    }

    public void setMin(List<ProducerInterval> min) {
        this.min = min;
    }

    public List<ProducerInterval> getMax() {
        return max;
    }

    public void setMax(List<ProducerInterval> max) {
        this.max = max;
    }

}