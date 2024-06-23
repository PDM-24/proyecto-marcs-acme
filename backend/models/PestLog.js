const mongoose = require('mongoose');

const PestLogSchema = new mongoose.Schema({
    crop: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Crop',
        required: true
    },
    description: {
        type: String,
        required: true
    },
    date: {
        type: Date,
        default: Date.now
    },
    actionsTaken: {
        type: String
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('PestLog', PestLogSchema);
