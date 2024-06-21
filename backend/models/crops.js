const mongoose = require('mongoose');

const GranjaSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    plantedDate: {
        type: Date,
        required: true
    },
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    notes: {
        type: String
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Crop', GranjaSchema);
