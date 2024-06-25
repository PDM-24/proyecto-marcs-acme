// models/comment.js
const mongoose = require('mongoose');

const CommentSchema = new mongoose.Schema({
    comment: {
        type: String,
        required: true
    },
    location: {
        type: String,
        required: true
    },
    cropType: {
        type: String,
        required: true
    },
    soilType: {
        type: String,
        required: true
    },
    observationDateTime: {
        type: Date,
        required: true
    },
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
        required: true
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Comment', CommentSchema);
