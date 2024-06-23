const mongoose = require('mongoose');

const SensorDataSchema = new mongoose.Schema({
    sensor_id: {
        type: Number,
        required: true
    },
    type: {
        type: String,
        required: true
    },
    value: {
        type: Number,
        required: true
    },
    timestamp: {
        type: Date,
        default: Date.now
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('SensorData', SensorDataSchema);
