// routes/commentRoutes.js
const express = require('express');
const router = express.Router();
const { getComments, addComment, deleteComment } = require('../controllers/commentController');
const authenticateToken = require('../middlewares/authenticateToken');

router.get('/get', authenticateToken, getComments);
router.post('/add', authenticateToken, addComment);
router.delete('/:id', authenticateToken, deleteComment);

module.exports = router;
