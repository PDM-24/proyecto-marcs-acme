const express = require('express');
const router = express.Router();
const { getCrops, getCropById, addCrop, updateCrop, deleteCrop } = require('../controllers/cropsController');
const authenticateToken = require('../middlewares/authenticateToken');

router.get('/get', authenticateToken, getCrops);
router.get('/:id', authenticateToken, getCropById);
router.post('/add', authenticateToken, addCrop);
router.put('/:id', authenticateToken, updateCrop);
router.delete('/delete/:id', authenticateToken, deleteCrop);

module.exports = router;
