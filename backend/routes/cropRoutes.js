const express = require('express');
const router = express.Router();
const { getCrops, getCropById, addCrop, updateCrop, deleteCrop } = require('../controllers/cropsController');

router.get('/', getCrops);
router.get('/:id', getCropById);
router.post('/add', addCrop);
router.put('/:id', updateCrop);
router.delete('/:id', deleteCrop);

module.exports = router;
