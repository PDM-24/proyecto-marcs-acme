const express = require('express');
const router = express.Router();
const { getPestLogs, getPestLogById, addPestLog, updatePestLog, deletePestLog } = require('../controllers/pestsController');

router.get('/', getPestLogs);
router.get('/:id', getPestLogById);
router.post('/add', addPestLog);
router.put('/:id', updatePestLog);
router.delete('/:id', deletePestLog);

module.exports = router;
