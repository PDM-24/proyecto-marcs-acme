const { body, validationResult } = require('express-validator');

const validateUser = [
    body('name').not().isEmpty().withMessage('Name is required'),
    body('role').not().isEmpty().withMessage('Role is required'),
    (req, res, next) => {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({ errors: errors.array() });
        }
        next();
    }
];

module.exports = validateUser;
